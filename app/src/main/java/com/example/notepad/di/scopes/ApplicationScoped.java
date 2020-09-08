package com.example.notepad.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Application scope
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScoped {
}
