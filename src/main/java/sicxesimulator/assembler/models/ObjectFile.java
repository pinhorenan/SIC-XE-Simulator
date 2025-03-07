package sicxesimulator.assembler.models;

public class ObjectFile {
    private final int startAddress; // Endereço de início (a palavra inicial, não o byte)
    private final byte[] objectCode; // Código objeto
    private final SymbolTable symbolTable;
    private final String fileName;

    public ObjectFile(int startAddress, byte[] objectCode, SymbolTable symbolTable, String fileName) {
        this.startAddress = startAddress;
        this.objectCode = objectCode;
        this.symbolTable = symbolTable;
        this.fileName = fileName;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public byte[] getObjectCode() {
        return objectCode;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public String getFilename() {
        return fileName;
    }

    public int getProgramLength() {
        return objectCode.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Endereço inicial: ").append(String.format("%04X", startAddress)).append("\n");
        sb.append("Tamanho do programa: ").append(getProgramLength()).append(" bytes\n");
        sb.append("Object Code: ");
        for (byte b : objectCode) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}
