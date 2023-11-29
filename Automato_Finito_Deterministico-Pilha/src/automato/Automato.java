package automato;

import classes.Transicao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Stephanye
 * @author Julio
 */
public class Automato {

    private static int printOpcoes() {
        System.out.println("Escolha uma das opcoes para o conjunto L:");
        System.out.println();
        System.out.println("1. L = {a^n b^n }");
        System.out.println("2. L = {a^n b^m a^n+m , m>0 }");
        System.out.println("3. L = {a^n b^m c^n+m }");
        System.out.println("4. L = {a^n b^m c^n-m}");
        System.out.println("5. L = {a^n b^n-m c^m}");
        System.out.print("Digite a opcao escolhida: ");
        Scanner Scanner=new Scanner(System.in);
        return (Scanner.nextInt());
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
       Transicao t=new Transicao();
        switch (printOpcoes()) {
            case 1:
                t.loadTransicao("anbn.csv");        
                t.imprimeTabela();
                t.fill();
                t.verificaCaracter("anbn.csv");
                break;
            case 2:
                t.loadTransicao("a^n b^m a^n+m.csv");        
                t.imprimeTabela();
                t.fill();
                t.verificaCaracter("a^n b^m a^n+m.csv");
                break;
            case 3:
                t.loadTransicao("a^nb^mc^n+m.csv");        
                t.imprimeTabela();
                t.fill();
                t.verificaCaracter("a^nb^mc^n+m.csv");
                break;
            case 4:
                t.loadTransicao("a^nb^mc^n-m.csv");        
                t.imprimeTabela();
                t.fill();
                t.verificaCaracter("a^nb^mc^n-m.csv");
                break;
            case 5:
                t.loadTransicao("a^nb^n-mc^m.csv");        
                t.imprimeTabela();
                t.fill();
                t.verificaCaracter("a^nb^n-mc^m.csv");
                break; 
            default:
              System.out.println("Opcao invalida.");
        }
    }
}