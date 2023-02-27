/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 *
 * @author dnlbe
 */
public class ReflectionUtils {

    public static void readClassInfo(Class<?> clazz, StringBuilder classInfo) {
        appendPackage(clazz, classInfo);
        appendModifiers(clazz, classInfo);
        appendParent(clazz, classInfo, true);
        appendInterfaces(clazz, classInfo);
    }

    private static void appendPackage(Class<?> clazz, StringBuilder classInfo) {
        classInfo
                .append("<h3><i>")
                .append(clazz.getPackage())
                .append("</i></h3>\n");
    }

    private static void appendModifiers(Class<?> clazz, StringBuilder classInfo) {

        classInfo
                .append("<h4>")
                .append(Modifier.toString(clazz.getModifiers()))
                .append(" ")
                .append(clazz.getSimpleName()).append("</h4>\n");
    }

    private static void appendParent(Class<?> clazz, StringBuilder classInfo, boolean first) {
        Class<?> parent = clazz.getSuperclass();
        if (parent == null) {
            return;
        }
        if (first) {
            classInfo.append("<b><i>extends</i></b>").append("<br><br>");
        }
        classInfo
                .append("<span class=\"tab\">")
                .append(parent.getName())
                .append("</span>")
                .append("<br>");
        appendParent(parent, classInfo, false);
    }

    private static void appendInterfaces(Class<?> clazz, StringBuilder classInfo) {
        if (clazz.getInterfaces().length > 0) {
            classInfo.append("<br><b><i>implements</i></b>").append("<br><br>");
        }
        for (Class<?> in : clazz.getInterfaces()) {
            classInfo
                    .append("<span class=\"tab\">")
                    .append(in.getName())
                    .append("</span>")
                    .append("<br>");
        }
        classInfo.append("</p>\n");
    }

    public static void readClassAndMembersInfo(Class<?> clazz, StringBuilder classAndMembersInfo) {
        readClassInfo(clazz, classAndMembersInfo);
        appendFields(clazz, classAndMembersInfo);
        appendMethods(clazz, classAndMembersInfo);
        appendConstructors(clazz, classAndMembersInfo);
    }

    private static void appendFields(Class<?> clazz, StringBuilder classAndMembersInfo) {
        //Field[] fields = clazz.getFields(); // returns public and inherited
        Field[] fields = clazz.getDeclaredFields(); // returns public, protected, default (package) access, and private fields, but excludes inherited fields
        classAndMembersInfo.append("<p><b><i>Fields:</i></b><br><br>");
        for (Field field : fields) {
            classAndMembersInfo
                    .append("<span class=\"tab\">")
                    .append(field)
                    .append("</span>")
                    .append("<br>");
        }
        classAndMembersInfo.append("</p>");
    }

    private static void appendMethods(Class<?> clazz, StringBuilder classAndMembersInfo) {
        Method[] methods = clazz.getDeclaredMethods();
        classAndMembersInfo.append("<p><b><i>Methods:</i></b><br><br>");
        for (Method method : methods) {
            classAndMembersInfo.append("<span class=\"tab\">");
            appendMethodAnnotations(method, classAndMembersInfo);
            classAndMembersInfo
                    .append(Modifier.toString(method.getModifiers()))
                    .append(" ")
                    .append(method.getReturnType())
                    .append(" ")
                    .append(method.getName());
            appendParameters(method, classAndMembersInfo);
            appendExceptions(method, classAndMembersInfo);
            classAndMembersInfo.append("</span>").append("<br>");
        }
        classAndMembersInfo.append("</p>");
    }

    private static void appendMethodAnnotations(Executable executable, StringBuilder classAndMembersInfo) {
        for (Annotation annotation : executable.getAnnotations()) {
            classAndMembersInfo
                    .append(annotation)
                    .append("<br>");
        }
    }

    private static void appendParameters(Executable executable, StringBuilder classAndMembersInfo) {
        classAndMembersInfo.append("(");
        for (Parameter parameter : executable.getParameters()) {
            classAndMembersInfo
                    .append(parameter)
                    .append(", ");
        }
        if (classAndMembersInfo.toString().endsWith(", ")) {
            classAndMembersInfo.delete(classAndMembersInfo.length() - 2, classAndMembersInfo.length());
        }
        classAndMembersInfo.append(")");
    }

    private static void appendExceptions(Executable executable, StringBuilder classAndMembersInfo) {
        Class<?>[] exceptionTypes = executable.getExceptionTypes();
        if (exceptionTypes.length > 0) {
            classAndMembersInfo.append(" <b>throws</b> ");
            for (Class<?> exceptionType : exceptionTypes) {
                classAndMembersInfo
                        .append("<i>")
                        .append(exceptionType)
                        .append("</i>")
                        .append(", ");
            }
            if (classAndMembersInfo.toString().endsWith(", ")) {
                classAndMembersInfo.delete(classAndMembersInfo.length() - 2, classAndMembersInfo.length());
            }
        }
    }

    private static void appendConstructors(Class<?> clazz, StringBuilder classAndMembersInfo) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        classAndMembersInfo.append("<p><b><i>Constructors:</i></b><br><br>");
        for (Constructor constructor : constructors) {
            classAndMembersInfo.append("<span class=\"tab\">");
            appendMethodAnnotations(constructor, classAndMembersInfo);
            classAndMembersInfo
                    .append(Modifier.toString(constructor.getModifiers()))
                    .append(" ")
                    .append(constructor.getName());
            appendParameters(constructor, classAndMembersInfo);
            appendExceptions(constructor, classAndMembersInfo);
            classAndMembersInfo.append("</span>").append("<br>");
        }
        classAndMembersInfo.append("</p>");
    }

}
