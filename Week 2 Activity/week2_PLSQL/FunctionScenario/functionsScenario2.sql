CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount IN NUMBER,
    p_annual_interest_rate IN NUMBER,
    p_loan_duration_years IN NUMBER
) RETURN NUMBER AS
    v_monthly_interest_rate NUMBER;
    v_number_of_payments NUMBER;
    v_monthly_installment NUMBER;
BEGIN
    -- Calculate the monthly interest rate (annual rate divided by 12 months and converted to a fraction)
    v_monthly_interest_rate := p_annual_interest_rate / 12 / 100;

    -- Calculate the total number of monthly payments
    v_number_of_payments := p_loan_duration_years * 12;

    -- Calculate the monthly installment using the formula for an amortizing loan
    IF v_monthly_interest_rate > 0 THEN
        v_monthly_installment := p_loan_amount * 
                                 (v_monthly_interest_rate * POWER(1 + v_monthly_interest_rate, v_number_of_payments)) /
                                 (POWER(1 + v_monthly_interest_rate, v_number_of_payments) - 1);
    ELSE
        -- If interest rate is zero, simply divide the loan amount by the number of payments
        v_monthly_installment := p_loan_amount / v_number_of_payments;
    END IF;

    RETURN v_monthly_installment;
END CalculateMonthlyInstallment;
