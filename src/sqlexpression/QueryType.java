package sqlexpression;

public enum QueryType {

    SELECT("SELECT"),
    INSERT("INSERT"),
    DELETE("DELETE"),
    UPDATE("UPDATE");

    private String _strCmd;

    /**
     * @return the _strCmd
     */
    public String getStrCmd() {
        return _strCmd;
    }

    /**
     * @param _strCmd the _strCmd to set
     */
    public void setStrCmd(String _strCmd) {
        this._strCmd = _strCmd;
    }

    QueryType(String command) {
       setStrCmd(command);
    }
    
    @Override
    public String toString()
    {
        return getStrCmd();
    }
}
