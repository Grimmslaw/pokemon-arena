package com.grimmslaw.pokemon.battle;

import com.grimmslaw.pokemon.model.AttackResult;
import com.grimmslaw.pokemon.model.Damager;
import com.grimmslaw.pokemon.model.Effector;
import com.grimmslaw.pokemon.moves.MoveEffect;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.util.PrettyPrintable;

import java.util.List;
import java.util.Objects;

public class Supervisor implements Damager, Effector, PrettyPrintable {

    private static Supervisor _supervisor;

    private Supervisor() {}

    public static Supervisor getInstance() {
        if (_supervisor == null) {
            _supervisor = new Supervisor();
        }
        return _supervisor;
    }

    private void resolveAttackResult(AttackResult attackResult) {
        // TODO: apply reciprocal effects to attacker (e.g. recoil)
        if (attackResult.attackShouldHit()) {
            applyDamageTo(attackResult.getTarget(), attackResult.getDamageToDeal());
            applyEffectTo(attackResult.getTarget(), attackResult.getEffectToApply());
        }
    }

    public void resolveOneTurnOfAttackResults(List<AttackResult> attackResultList) {
        for (AttackResult result : attackResultList) {
            resolveAttackResult(result);
        }
    }

    @Override
    public void applyEffectTo(Pokemon target, MoveEffect effectToApply) {
        // TODO
    }

    @Override
    public void applyDamageTo(Pokemon target, int damageToDeal) {
        // TODO: implement any necessary checks here
        if (target.receiveDamage(damageToDeal) == 0.0) {
            target.doFaint();
        }
    }

    @Override
    public void makeFaint(Pokemon target) {
        // TODO: implement any necessary checks here
        target.doFaint();
    }

}
