# ParkShark

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


# Kanban
https://trello.com/b/Jpy076m6/parkshark