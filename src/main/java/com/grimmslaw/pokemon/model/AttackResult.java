package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.moves.MoveEffect;
import com.grimmslaw.pokemon.pokemon.Pokemon;

import java.util.Objects;
import java.util.StringJoiner;

public class AttackResult {

    private Pokemon attacker;
    private Pokemon target;
    private boolean attackShouldHit;
    private int damageToDeal;
    private MoveEffect effectToApply;

    public AttackResult(Pokemon attacker, Pokemon target, boolean attackShouldHit,
                        int damageToDeal, MoveEffect effectToApply) {
        this.attacker = attacker;
        this.target = target;
        this.attackShouldHit = attackShouldHit;
        this.damageToDeal = damageToDeal;
        this.effectToApply = effectToApply;
    }

    public Pokemon getAttacker() {
        return attacker;
    }

    public Pokemon getTarget() {
        return target;
    }

    public void setTarget(Pokemon target) {
        this.target = target;
    }

    public boolean attackShouldHit() {
        return attackShouldHit;
    }

    public int getDamageToDeal() {
        return damageToDeal;
    }

    public MoveEffect getEffectToApply() {
        return effectToApply;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AttackResult.class.getSimpleName() + "[", "]")
                .add("attacker=" + attacker)
                .add("target=" + target)
                .add("attackShouldHit=" + attackShouldHit)
                .add("damageToDeal=" + damageToDeal)
                .add("effectToApply=" + effectToApply)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttackResult that = (AttackResult) o;
        return Objects.equals(attacker, that.attacker) &&
                Objects.equals(target, that.target) &&
                attackShouldHit == that.attackShouldHit &&
                damageToDeal == that.damageToDeal &&
                Objects.equals(effectToApply, that.effectToApply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attacker, target, attackShouldHit, damageToDeal, effectToApply);
    }

}
