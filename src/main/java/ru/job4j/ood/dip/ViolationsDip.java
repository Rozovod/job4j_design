package ru.job4j.ood.dip;

abstract class Tool {
    protected String name;

    public Tool(String name) {
        this.name = name;
    }
}

class Shovel extends Tool {
    public Shovel(String name) {
        super(name);
    }
}

class Tractor extends Tool {
    public Tractor(String name) {
        super(name);
    }
}

class ConcreteMixer extends Tool {
    public ConcreteMixer(String name) {
        super(name);
    }
}

interface IWork {
    boolean doWork(Tool tool);
}

class Dig implements IWork {
    @Override
    public boolean doWork(Tool tool) {
        return false;
    }
}

class MixConcrete implements IWork {
    @Override
    public boolean doWork(Tool tool) {
        return false;
    }
}

/**
 * Первое нарушение. В этом классе поля представлены не абстракциями, а реализациями.
 * Второе нарушение. Аргументы конструктора представлдены не абстракциями, а реализациями.
 */

class Construction {
    private Shovel shovel;
    private Tractor tractor;
    private ConcreteMixer concreteMixer;

    private Dig dig;
    private MixConcrete mixConcrete;

    public Construction(Shovel shovel, Tractor tractor, ConcreteMixer concreteMixer, Dig dig, MixConcrete mixConcrete) {
        this.shovel = shovel;
        this.tractor = tractor;
        this.concreteMixer = concreteMixer;
        this.dig = dig;
        this.mixConcrete = mixConcrete;
    }

    /**
     * Третьим нарушением является аргумент метода, представленный не абстракцией, а реализацией.
     * В связи с этим, мы не можем использовать для этого действия, например, трактор.
     */

    public void digHole(Dig dig, Shovel shovel) {
        /* какая-то логика */
    }

    /**
     * Четвертым нарушением является возвращаемое значение этого метода, которое представлено не интерфейсом,
     * а конкретной реализацией.
     */

    public MixConcrete mix(Tool tool) {
        MixConcrete mixConcrete = new MixConcrete();
        /* какая-то логика */
        return mixConcrete;
    }
}
