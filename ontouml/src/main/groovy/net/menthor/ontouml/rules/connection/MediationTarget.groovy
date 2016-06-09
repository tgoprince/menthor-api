package net.menthor.ontouml.rules.connection
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
import net.menthor.ontouml.rules.SyntacticalRule
import net.menthor.ontouml.OntoUMLClass

/**
 * @author John Guerson
 */
class MediationTarget implements SyntacticalRule {

    MediationTarget(OntoUMLRelationship self){
        this.errorMsg = 'The mediated type (target) of a Mediation must be a Rigid Sortal (Kind, Collective Quantity, or SubKind), Category, Role or RoleMixin'
        this.self = self
    }

    @Override
    boolean condition() {
        def rel = self as OntoUMLRelationship
        if(rel.isMediation()){
            def tgtClass = (rel.targetClass() as OntoUMLClass)
            return tgtClass!=null && (tgtClass.isSubstanceSortalClass() || tgtClass.isSubKind() ||
                   tgtClass.isCategory() || tgtClass.isRole() || tgtClass.isRoleMixin())
        }
        return true
    }

    @Override
    boolean quickFix(){
        return false
    }
}