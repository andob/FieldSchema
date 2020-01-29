package com.example.yatatsu.libproject;

import com.yatatsu.fieldschema.annotations.FieldSchemaClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "app", name = "_dog")
@FieldSchemaClass
public class Dog
{
  @Column(name = "_id")
  public int id;

  @Column(name = "name")
  public String name;

  @Column(name = "age")
  public int age;
}
