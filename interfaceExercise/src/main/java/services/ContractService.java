package services;

import java.time.LocalDate;
import model.Contract;
import model.Installment;

public class ContractService {

    private OnlinePaymentService onlinepaymentservice;

    public ContractService(OnlinePaymentService onlinepaymentservice) {
        this.onlinepaymentservice = onlinepaymentservice;
    }

    public void processContract(Contract contract, int months) {

        double basicQuota = contract.getTotalValue() / months;
        for (int i = 0; i < months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double interest = onlinepaymentservice.interest(basicQuota, i);
            double fee = onlinepaymentservice.paymentFee(basicQuota + interest);

            double quota = basicQuota + interest + fee;

            contract.getInstallment().add(new Installment(dueDate, quota));
        }
    }
}
