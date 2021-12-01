package userType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

interface cmdMapToDesc {
    Map <String,Runnable> cmdMap = new HashMap <>();
    BiFunction <String,String,String> commandToDescription = (cmd,desc) -> {
        System.console().printf(desc); return cmd;
    };

    void FunctionMapping();
}
