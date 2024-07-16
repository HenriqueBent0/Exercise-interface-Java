package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import model.Contract;
import model.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Entre com os dados do contrato");
        System.out.print("Numero: ");
        int number = scan.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(scan.next(), fmt);
        System.out.print("Valor do contrato: ");
        double totalValue = scan.nextDouble();
        
        Contract contract = new Contract(number, date, totalValue);
        
        System.out.print("Entre com o numero de parcelas: ");
        int n = scan.nextInt();
        
        ContractService contractService = new ContractService(new PaypalService()); 
        
        contractService.processContract(contract, n);
        
        System.out.println("Parcelas: ");
        for(Installment installment : contract.getInstallment()){
            System.out.println(installment);
        }
        scan.close();
    }
}
