package by.tms.server.service;

import by.tms.server.console.ConsoleWriter;
import by.tms.server.model.Operation;

public class OperationService {
    ConsoleWriter consoleWriter = new ConsoleWriter();
    public Operation calculate(Operation operation){
        switch (operation.getType()){
            case "sum":
                operation.setResult(operation.getNum1()+operation.getNum2());
                return operation;
            case "min":
                operation.setResult(operation.getNum1() - operation.getNum2());
                return operation;
            case "multi":
                operation.setResult(operation.getNum1() * operation.getNum2());
                return operation;
            case "div":
                if(operation.getNum2() !=0) {
                    operation.setResult(operation.getNum1() / operation.getNum2());
                    return operation;
                }
            default:
                consoleWriter.write("invalid operation");
        }
        throw new RuntimeException();
    }
}
