package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate nandBack;
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;

    public DeMux() {
        super("DeMux", 2, 2);
        nandBack = new NandGate();
        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
        nand4 = new NandGate();

        nand1.connect(1, nandBack);
        nand2.connect(0, nand1);
        nand2.connect(1, nand1);

        nand4.connect(0, nand3);
        nand4.connect(1, nand3);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nand2.read();
        } else if (outputPin == 1) {
            return nand4.read();
        } else {
            return false;
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nand1.connect(0, emitter);
                nand3.connect(1, emitter);
                break;
            case 1:
                nandBack.connect(0, emitter);
                nandBack.connect(1, emitter);
                nand3.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
