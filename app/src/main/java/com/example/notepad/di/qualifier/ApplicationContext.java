package com.example.notepad.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 *Annotation define a application context object
 */
@Qualifier
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationContext {
}
