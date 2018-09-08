package top.hookan.cocoa.registry.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CocoaReg
{
    /**
     * Main class of mod
     */
    Class value();

    /**
     * Register impl
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Reg
    {
        /**
         * Register name
         */
        String value();
    }

    /**
     * Register item model and block model
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface ModelReg
    {
        /**
         * Item or block model name
         */
        String value();
    }

    /**
     * Register ore dictionary
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface OreDicReg
    {
        /**
         * Ore dictionary name
         */
        String value();
    }

    /**
     * Register dimension
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface DimReg
    {
        /**
         * ID of dimension
         */
        int value();
    }

    /**
     * Register command
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface CommandReg
    {
    }
}
