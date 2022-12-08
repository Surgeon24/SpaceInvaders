package L7.Gr06.common;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public class Upgrades {
    List <Triplet> gunSpeed = new ArrayList<>();
    List <Triplet> gunPower = new ArrayList<>();
    List <Triplet> armor = new ArrayList<>();

    public Upgrades(){
        gunSpeed.add(new Triplet<>(100,"VERY SLOW", 0));
        gunSpeed.add(new Triplet<>(300,"SLOW", -100));
        gunSpeed.add(new Triplet<>(500,"BURST", -100));
        gunSpeed.add(new Triplet<>(800,"MACHINE GUN", -200));
        gunSpeed.add(new Triplet<>(2000,"LASER", -200));
        gunSpeed.add(new Triplet<>(0,"BEST", 0));

        gunPower.add(new Triplet<>(100,"48MM", 0));
        gunPower.add(new Triplet<>(300,"60MM", 1));
        gunPower.add(new Triplet<>(500,"76MM", 1));
        gunPower.add(new Triplet<>(700,"88MM", 2));
        gunPower.add(new Triplet<>(1500,"96MM", 2));
        gunPower.add(new Triplet<>(3000,"120MM",3));
        gunPower.add(new Triplet<>(0,"BEST",0));

        armor.add(new Triplet<>(100,"THE PAST CENTURY", 0));
        armor.add(new Triplet<>(300,"OBSOLETE", 1));
        armor.add(new Triplet<>(500,"STANDARD", 1));
        armor.add(new Triplet<>(700,"MODERNIZED", 2));
        armor.add(new Triplet<>(2000,"SECRET DEVELOPMENT", 3));
        armor.add(new Triplet<>(0,"BEST",0));

    }
}
