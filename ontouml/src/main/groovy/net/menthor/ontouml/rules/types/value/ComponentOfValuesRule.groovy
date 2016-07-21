package net.menthor.ontouml.rules.types.value

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016  Menthor Consulting in Information Technology
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

import net.menthor.ontouml.OntoUMLRelationship
import net.menthor.ontouml.rules.generic.GenericCondition
import net.menthor.ontouml.rules.ValueSyntacticalRule
import net.menthor.ontouml.values.ReflexivityValue
import net.menthor.ontouml.values.TransitivityValue

/**
 * @author John Guerson
 */
class ComponentOfValuesRule implements ValueSyntacticalRule {

    ComponentOfValuesRule(OntoUMLRelationship self){
        this.description = 'A ComponentOf must be Non-Reflexive and Non-Transitive'
        this.self = self
    }

    @Override
    boolean condition() {
        return GenericCondition.isValue(self, "isComponentOf", ReflexivityValue.NON_REFLEXIVE, null,
            TransitivityValue.NON_TRANSITIVE, null)
    }

    @Override
    boolean quickFix(){
        (this.self as OntoUMLRelationship).setReflexivityValue(ReflexivityValue.NON_REFLEXIVE)
        (this.self as OntoUMLRelationship).setTransitivityValue(TransitivityValue.NON_TRANSITIVE)
        return true
    }
}