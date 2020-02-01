package com.yatatsu.fieldschema.processor_room;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.util.Set;

public class FieldSchemaHolder {

  private final String name;
  private final Element element;
  private final FieldSchemaClassHolder classHolder;
  private String columnName;

  public FieldSchemaHolder(Element element, FieldSchemaClassHolder classHolder, String prefix) {
    this.element = element;
    this.classHolder = classHolder;
    this.name = prefix + "_" + element.toString();
    this.columnName = element.toString();

    for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
      if (annotationMirror.getAnnotationType().toString().equals("androidx.room.ColumnInfo")) {
        Set<? extends ExecutableElement> keys=annotationMirror.getElementValues().keySet();
        for (ExecutableElement key : keys) {
          AnnotationValue value=annotationMirror.getElementValues().get(key);
          if (key.toString().startsWith("name"))
            this.columnName=value.getValue().toString();
        }
      }
    }
  }

  public String getName() {
    return name;
  }

  public Element getElement() {
    return element;
  }

  public FieldSchemaClassHolder getClassHolder() {
    return classHolder;
  }

  public String getColumnName() {
    return columnName;
  }

  @Override public boolean equals(Object o) {
    return o == this
        || (o instanceof FieldSchemaHolder && ((FieldSchemaHolder) o).name.equals(name));
  }

  @Override public int hashCode() {
    return name.hashCode();
  }
}
