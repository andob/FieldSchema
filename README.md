# FieldSchema

[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/yatatsu/FieldSchema.svg?branch=master)](https://travis-ci.org/yatatsu/FieldSchema)

Generate field name constant for Android.

## Usage

FieldSchema helps you accessing field name in static.

```java
@FieldSchemaClass(name = "myTodo") public class Todo {
  public String name;
  public String description;
  public Date createdAt;
  public String version;
}
```

Here is generated code.

```java
public final class FS {
  public static final String mytodo_name = "name";

  public static final String mytodo_description = "description";

  public static final String mytodo_createdAt = "createdAt";

  public static final String mytodo_version = "version";
}
```

You can use `FS.todo_name` instead of `"name"`.

Constants named `{class name}_{field name}` in `FS.java`. To avoid collision name, Use `name` option. (The default is class name)

```java
@FieldSchemaClass(name = "myTodo") public class Todo {
  public String name;
}
```

Here is generated.

```java
public final class FS {
  public static final String mytodo_name = "name";
}
```

### Use in library project

When using in library project, `FS.java` cause collision because it has fixed package `com.yatatsu.fieldschema`.
Use support apt option to avoid this, here is example.

```build.gradle
apt {
  arguments {
    fieldSchemaPackage 'com.example.app'
  }
}
```

And generate class is `com.example.app.FS`. The default package is `com.yatatsu.fieldschema`.

## Download

```
repositories {
    maven { url 'https://maven.andob.info/repository/open_source' }
}
```

```
implementation 'ro.andob.fieldschema:annotations:0.3.4'
kapt 'ro.andob.fieldschema:fs-processor:0.3.4' //for FS
kapt 'ro.andob.fieldschema:ts-processor-jpa:0.3.4' //for TS/JPA
kapt 'ro.andob.fieldschema:ts-processor-room:0.3.4' //for TS/ROOM
//note: you can't use both TS/JPA and TS/ROOM in the same module
```

## Fork additions

1. Added class names constants
2. Added TS class containing table name / table column name constants (from JPA or androidx.room annotations)

```kotlin
@Entity
@Table(name = "_user", schema = "app")
@FieldSchemaClass
class User
(
    @Id
    @Column(name = "_id")
    val id : String = uuid(),

    @Column(name = "name")
    val name : String = ""
)
```

Result:

```kotlin
public class FS
{
    public static final String User = "User";
    public static final String User_id = "id";
    public static final String User_name = "name";
}
```

```kotlin
public class TS
{
    public static final String User = "app._user";
    public static final String User_id = "_id";
    public static final String User_name = "name";
}
```

Import the forked version with:

```
repositories {
    maven { url 'https://maven.andob.info/repository/open_source' }
}
```

```
implementation 'ro.andob.fieldschema:annotations:0.3.4'
kapt 'ro.andob.fieldschema:fs-processor:0.3.4' //for FS
kapt 'ro.andob.fieldschema:ts-processor-jpa:0.3.4' //for TS/JPA
kapt 'ro.andob.fieldschema:ts-processor-room:0.3.4' //for TS/ROOM
//note: you can't use both TS/JPA and TS/ROOM in the same module
```


## License

```
Copyright 2016 KITAGAWA, Tatsuya

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
