package com.yatatsu.fieldschema.processor_room;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;

public class FieldSchemaClassHolder {

  private final TypeElement typeElement;
  private final TypeName typeName;
  private final List<FieldSchemaHolder> fieldSchemaHolders;
  private final String name;
  private String tableName;

  public FieldSchemaClassHolder(TypeElement typeElement, String name) {
    this.typeElement = typeElement;
    this.typeName = ClassName.get(typeElement);
    if (name == null || name.length() == 0) {
      this.name = getSimpleClassName();
    } else {
      this.name = name.toLowerCase();
    }

    this.tableName = this.name;

    for (AnnotationMirror annotationMirror : typeElement.getAnnotationMirrors()) {
      if (annotationMirror.getAnnotationType().toString().equals("androidx.room.Entity")) {
        Set<? extends ExecutableElement> keys = annotationMirror.getElementValues().keySet();
        for (ExecutableElement key : keys) {
          AnnotationValue value = annotationMirror.getElementValues().get(key);
          if (key.toString().startsWith("tableName"))
            this.tableName = value.getValue().toString();
        }
      }
    }

    this.fieldSchemaHolders = new LinkedList<>();

    do
    {
      if (typeElement!=null)
      {
        this.fieldSchemaHolders.addAll(findAllNonPrivateFields(typeElement));
        typeElement = (TypeElement)((DeclaredType)typeElement.getSuperclass()).asElement();
        if (typeElement.getSimpleName().toString().equals("Object"))
          typeElement = null;
      }
    }
    while(typeElement!=null);
  }

  public TypeElement getTypeElement() {
    return typeElement;
  }

  public TypeName getTypeName() {
    return typeName;
  }

  public List<FieldSchemaHolder> getFieldSchemaHolders() {
    return fieldSchemaHolders;
  }

  public String getQualifiedClassName() {
    return typeElement.getQualifiedName().toString();
  }

  public String getSimpleClassName() {
    return typeElement.getSimpleName().toString();
  }

  public String getName() {
    return name;
  }

  public String getTableName() {
    return tableName;
  }

  private List<FieldSchemaHolder> findAllNonPrivateFields(Element element) {
    return element.getEnclosedElements()
        .stream()
        .filter(
            e -> e.getKind() == ElementKind.FIELD && !e.getModifiers().contains(Modifier.STATIC))
        .map(e -> new FieldSchemaHolder(e, this, name))
        .collect(Collectors.toList());
  }
}
