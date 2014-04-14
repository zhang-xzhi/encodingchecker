package allen.model;

/**
 * 程序动作。
 * 
 * <pre>
 * InitProgram
 * FileLoad
 * ChangeEncoding
 * </pre>
 * */
public enum Action {

    /**
     * 重置程序为初始状态.
     * */
    InitProgram,
    /**
     * 加载文件。
     * */
    FileLoad,
    /**
     * 改变编码。
     * */
    ChangeEncoding;
}
