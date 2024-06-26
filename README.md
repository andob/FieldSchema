## FieldSchema

Annotation processor to generate class / field / table / column name constants.

### Example

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

@Entity
@Table(name = "_note", schema = "app")
@FieldSchemaClass
class Note
(
    @Id
    @Column(name = "_id")
    val id : String = uuid(),

    @Column(name = "title")
    val title : String = ""
    
    @Column(name = "contents")
    val contents : String = ""
)
```

Will generate the following classes:

```java
public class FS
{
    public static final String User = "User";
    public static final String User_id = "id";
    public static final String User_name = "name";
    public static final String Note = "Note";
    public static final String Note_id = "id";
    public static final String Note_title = "title";
    public static final String Note_contents = "contents";
}
```

```java
public class TS
{
    public static final String User = "app._user";
    public static final String User_id = "_id";
    public static final String User_name = "name";
    public static final String Note = "app._note";
    public static final String Note_id = "_id";
    public static final String Note_title = "title";
    public static final String Note_contents = "contents";
}
```

- ``FS.<className>`` and ``FS.<className>_<fieldName>`` from class and field names. Useful for reflection.
- ``TS.<tableName`` and ``TS.<tableName>_<columnName>`` from table and column names (extracted from JPA annotations or Google ROOM annotations). Useful for raw queries.

### Import

```
repositories {
    maven { url 'https://andob.io/repository/open_source' }
}
```

```
implementation 'ro.andob.fieldschema:annotations:0.3.4'
kapt 'ro.andob.fieldschema:fs-processor:0.3.4' //for FS
kapt 'ro.andob.fieldschema:ts-processor-jpa:0.3.4' //for TS/JPA
kapt 'ro.andob.fieldschema:ts-processor-room:0.3.4' //for TS/ROOM
//note: you can't use both TS/JPA and TS/ROOM in the same module
```

### This is a fork, you can find the original library [here](https://github.com/yatatsu/FieldSchema)

### License

```
Copyright 2016 KITAGAWA Tatsuya, 2017-2021 Dobrescu Andrei

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
