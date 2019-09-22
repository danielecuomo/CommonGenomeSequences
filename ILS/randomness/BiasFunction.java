package randomness;

public enum BiasFunction{
    EXP{ public double bias(double g){ return Math.exp(-g); } },
    LOG{ public double bias(double g){ return 1.0/Math.log(g + 2.0); } },
    POLY{ public double bias(double g){ return Math.pow(1.0 + g, -1.0); } },
    N{ public double bias(double g){ return (1.0/Math.sqrt(2.0*Math.PI))*Math.exp(-Math.pow(g, 2.0)/2.0); } },
    U{ public double bias(double g){ return 1.0; } },
    I{ public double bias(double g){ return 1/g; } };

    public abstract double bias(double g);
}