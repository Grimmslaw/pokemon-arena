package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.moves.MoveEffect;
import com.grimmslaw.pokemon.pokemon.Pokemon;

public interface Effector {

    void applyEffectTo(Pokemon target, MoveEffect effectToApply);

}
