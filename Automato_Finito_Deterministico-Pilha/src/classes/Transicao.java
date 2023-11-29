package classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Stephanye
 * @author Julio
 */
public class Transicao {
    private int origem;
    private char letra;
    private int destino;
    private List<Transicao> lista;
    private String palavra;
    private char[] caracter;
    private String empilha;
    private String desempilha;
    private Stack<String> Pilha;
    
    public Transicao(){
        this.origem=0;
        this.letra='z';
        this.destino=0;
        this.lista= new ArrayList<>();
        this.palavra="a";
        this.caracter=new char[0];
        this.empilha="z";
        this.desempilha="z";
        this.Pilha=new Stack<>();
    }

    public void setOrigem(int origem){
       this.origem=origem;
    }
   
    public int getOrigem(){
       return origem;
    }
   
    public void setLetra(char letra){
       this.letra=letra;
    }
   
    public char getLetra(){
       return letra;
    }
   
    public void setDestino(int destino){
       this.destino=destino;
    }
   
    public int getDestino(){
       return destino;
    }

    public void setLista(List<Transicao> lista){
       this.lista=lista;
    }
   
    public List<Transicao> getTransicao(){
       return lista;
    }
    
    public void setPalavra(String palavra){
       this.palavra=palavra;
    }
    
    public String getPalavra(){
       return palavra;
    }
   
    public void setCaracter(int i, char caracter){
       this.caracter[i]=caracter;
    }
   
    public char getCaracter(int i){
       return caracter[i];
    } 
        
    public String getEmpilha(){
        return empilha;
    }
    
    public void setEmpilha(String empilha){
        this.empilha=empilha;
    }
    
    public String getDesempilha(){
        return desempilha;
    }
    
    public void setDesempilha(String desempilha){
        this.desempilha=desempilha;
    }
    
    
    public void fill(){
       System.out.println("Digite uma palavra");
       Scanner Scanner = new Scanner(System.in);
       this.palavra=Scanner.nextLine();
    }
   
    public void cvsParaAtributo(String csv){
       Transicao t=new Transicao();
       String vetor[]=csv.split(",");
       this.origem = Integer.parseInt(vetor[0]);
       this.letra = vetor[1].charAt(0);
       this.destino = Integer.parseInt(vetor[2]);
       this.empilha = vetor[3];
       this.desempilha = vetor[4];
       t.setOrigem(origem);
       t.setLetra(letra);
       t.setDestino(destino);
       t.setEmpilha(empilha);
       t.setDesempilha(desempilha);
    }

    public void loadTransicao(String arquivo) throws FileNotFoundException, IOException{
        FileReader f=null;
        this.lista = new ArrayList<>();
        f=new FileReader(arquivo);
        try (Scanner arquivoLido = new Scanner(f)) {
            arquivoLido.useDelimiter("\n");
            String linhaLida=arquivoLido.nextLine();
            while (arquivoLido.hasNext()) {
                Transicao t=new Transicao();
                linhaLida = arquivoLido.nextLine().replaceAll("q", "").replaceAll("final", "-1");
                t.cvsParaAtributo(linhaLida);
                lista.add(t);
            }
            f.close();
        }  
    }
   
    public void imprimeTabela() {
        System.out.println("Lista de transicoes:");
        System.out.printf("+------------+------------+------------+------------+-------------+\n");
        System.out.printf("|   Origem   |   Letra    |  Destino   |  Empilha   | Desempilha  |\n");
        System.out.printf("+------------+------------+------------+------------+-------------+\n");

        for (Transicao t : lista) {
            if(t.destino!=-1){
                System.out.printf("| q%-10d| %10c | q%-10d| %-10s | %-10s  |\n",t.origem,t.letra,t.destino,t.empilha,t.desempilha);
            }else{
                System.out.printf("| q%-10d| %10c | %-10s | %-10s | %-10s  |\n",t.origem,t.letra,"qf",t.empilha,t.desempilha);
            }
            System.out.printf("+------------+------------+------------+------------+-------------+\n");
        }
    }
    
    public int encontrarTransicao(int origemPalavra,char letraPalavra,Stack<String> Pilha){
        for (Transicao t : lista) {
            if(t.origem==origemPalavra && t.letra==letraPalavra){
                if( t.destino!=-1){ 
                    if(!"nao".equals(t.empilha)){
                        Pilha.push(t.empilha);
                    }
                    if(!"nao".equals(t.desempilha)){
                       if(t.desempilha.equals(Pilha.peek()) && Pilha.isEmpty()!=true){
                           Pilha.pop(); 
                        }else{
                            return -1;
                        }
                    }
                    return t.destino;
                }
            }
        }
        return -1;
    }
    
    public boolean verificaEstado(int estadoFinalPalavra){
        for (Transicao t : lista) {
            if(estadoFinalPalavra==t.origem && t.destino==-1){
                return true;
            }
        }
        return false;
    }
    
    public void verificaCaracter(String arquivo) throws FileNotFoundException, IOException{
       char[] caracter=palavra.toCharArray();
       Transicao t=new Transicao();
       t.loadTransicao(arquivo);
       for(int i=0; i < palavra.length(); i++){
            origem=(t.encontrarTransicao(origem, caracter[i], Pilha));
            System.out.println("Estado: " + origem);
            System.out.println("Pilha: "+ Pilha);
            if(origem==-1){
                break;           
            }
        }

       if(t.verificaEstado(origem) && Pilha.isEmpty()){
            System.out.println("Palavra aceita");
        }else{
            System.out.println("Palavra nao aceita");
       }
    }
}    