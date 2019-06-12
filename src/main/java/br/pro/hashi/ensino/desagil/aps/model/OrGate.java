package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    NandGate nandTop;
    NandGate nandBottom;
    NandGate nandRight;

    public OrGate() {
        super("OR", 2, 1);

        nandTop = new NandGate();
        nandBottom = new NandGate();
        nandRight = new NandGate();
        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandRight.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandTop.connect(1, emitter);
                break;
            case 1:
                nandBottom.connect(0, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}