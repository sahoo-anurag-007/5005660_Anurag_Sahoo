CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    -- Insert a record into the AuditLog table
    INSERT INTO AuditLog (transaction_id, account_id, transaction_type, amount, transaction_date, audit_date)
    VALUES (:NEW.transaction_id, :NEW.account_id, :NEW.transaction_type, :NEW.amount, :NEW.transaction_date, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Audit log entry created for Transaction ID: ' || :NEW.transaction_id);
END LogTransaction;
