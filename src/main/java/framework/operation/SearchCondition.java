package framework.operation;

import framework.model.Account;

/**
 *
 * @author malalanayake
 */
public class SearchCondition implements Predicate<Account> {

    private String accountNo;

    public SearchCondition(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public boolean check(Account data) {
         return data.getAccountNo().equals(accountNo);
    }

}
