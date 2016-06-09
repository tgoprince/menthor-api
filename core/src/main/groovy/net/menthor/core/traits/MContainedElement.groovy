package net.menthor.core.traits

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import net.menthor.core.MComment
import net.menthor.core.MPackage

/**
 * Menthor CORE Contained Element
 * @author John Guerson
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
trait MContainedElement extends MElement {

    protected MContainer container
    protected List<MComment> comments = []

    //=============================
    // Getters
    //=============================

    @JsonIgnore
    MContainer getContainer() { return container }

    List<MComment> getComments() { return comments }

    //=============================
    // Setters were overwritten to ensure
    // opposite ends in the metamodel
    //=============================

    void setContainer(MContainer c){
        container = c
        if(c==null) return
        //Ensure the opposite end
        if(!c.elements.contains(this)) {
            c.elements.add(this)
        }
    }

    void setComment(MComment c){
        if(c==null) return
        if(!comments.contains(c)){
            comments.add(c)
        }
        //Ensuring opposite end
        c.setOwner(this)
    }

    void setComments(List<MComment> comments){
        if(comments==null || comments == []){
            this.comments.clear()
            return
        }
        comments.each{ c ->
            setComment(c)
        }
    }

    //=============================
    // Root
    //=============================

    /** The model container */
    MPackage model(){
        return model(container)
    }

    /** The roo container searching from a given container */
    MPackage model(MPackage c){
        if(c.getContainer()==null) {
            return c
        }else{
            return model(c.container)
        }
    }
}