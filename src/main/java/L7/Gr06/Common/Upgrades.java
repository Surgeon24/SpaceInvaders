package L7.Gr06.Common;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public class Upgrades {
    List <Triplet> gunSpeed = new ArrayList<>();
    List <Triplet> gunPower = new ArrayList<>();
    List <Triplet> armor = new ArrayList<>();

    public Upgrades(){
        gunSpeed.add(new Triplet<>(100,"VERY SLOW", 0));
        gunSpeed.add(new Triplet<>(200,"SLOW", -100));
        gunSpeed.add(new Triplet<>(400,"BURST", -100));
        gunSpeed.add(new Triplet<>(800,"MACHINE GUN", -200));
        gunSpeed.add(new Triplet<>(1600,"LASER", -200));
        gunSpeed.add(new Triplet<>(0,"BEST", 0));

        gunPower.add(new Triplet<>(100,"48MM", 0));
        gunPower.add(new Triplet<>(200,"60MM", 1));
        gunPower.add(new Triplet<>(400,"76MM", 1));
        gunPower.add(new Triplet<>(800,"88MM", 2));
        gunPower.add(new Triplet<>(1600,"96MM", 2));
        gunPower.add(new Triplet<>(3200,"120MM",3));
        gunPower.add(new Triplet<>(0,"BEST",0));

        armor.add(new Triplet<>(100,"THE PAST CENTURY", 0));
        armor.add(new Triplet<>(200,"OBSOLETE", 1));
        armor.add(new Triplet<>(400,"STANDARD", 1));
        armor.add(new Triplet<>(600,"MODERNIZED", 2));
        armor.add(new Triplet<>(800,"SECRET DEVELOPMENT", 3));
        armor.add(new Triplet<>(0,"BEST",0));

    }
}
