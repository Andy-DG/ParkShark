# ParkShark

https://park-shark-apr-2022.herokuapp.com/swagger-ui/index.html

# Coding Conventions

- Database -> Flyway
- Coding conventions: Validation/Assertions , DTO's, Records(?), MapperNames, 2 Test files per class (Unit <=> Integration)



OBJECT-DIRECTORY
- api -> dto's + Controller
- domain -> Class + Repo
- service -> Service + Mapper

Abstract prefix
Iedere exception/error loggen
log on creation


FOR DTO'S
```
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class CreateItemDTO{
private String name;
private String description;
private double price;
private int stock;
}
```


# Kanban
https://trello.com/b/Jpy076m6/parkshark


MOCKING:
-> Only use mock in testmethods when it's required to mock a something, otherwise it breaks everything. 

# READMECEPTION
https://github.com/switchfully/track-shared/tree/master/60-projects/20-parkshark


# QUESTIONS FOR CHRISTOPHE
- integration test!!! full walkthrough
- 2 pintjes (Brecht still owes one)
- waarom moet de variable name specifiek zijn bij @joincolumn, zie membershiplevel bij member
- waarom moet ge de name specifieren bij @joincolumn als dat genegeerd wordt / wat moet er in de application properties worden toegevoegd.
- max 7 parameters in constructor? -> builder? setter?
- cascade types? error when adding parking lot (something about the employee)
