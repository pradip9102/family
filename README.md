# Meet The Family

## Capabilities
+ Given a 'name' and a 'relationship', output the people corresponding to the relationship in the order in which they were added in the family tree.
+ Add a child to any family in the tree through the mother.

## How to run


## I/O Format
| Format | Input | Output |
|:-------|:------|:-------|
| Add a child | ADD_CHILD ”Mother’s-Name" "Child's-Name" "Gender" | CHILD_ADDITION_SUCCEEDED |
| | e.g. `ADD_CHILD Chitra Aria Female` | `CHILD_ADDITION_SUCCEEDED` |
| | | |
| Find people belonging to a relationship | GET_RELATIONSHIP ”Name” “Relationship” | ”Name 1” “Name 2”... “Name N" |
| | e.g. `GET_RELATIONSHIP Lavnya Maternal-Aunt` | `Aria` |
| | e.g. `GET_RELATIONSHIP Aria Siblings` | `Jnki Ahit` |

## Sample I/O scenarios
| Sample | Input | Output |
|-------:|:------|:-------|
| 1. | `ADD_CHILD Pjali Srutak Male` | `PERSON_NOT_FOUND` |
| 2. | `GET_RELATIONSHIP Pjali Son` | `PERSON_NOT_FOUND` |
| 3. | `ADD_CHILD Asva Vani Female` | `CHILD_ADDITION_FAILED` |
| 4. | `GET_RELATIONSHIP Vasa Siblings` | `NONE` |
| 5. | `GET_RELATIONSHIP Atya Sister-In-Law` | `Krpi Satvy` |

## Relationships to handle
| Relationships | Definition |
|:--------------|:-----------|
| `Paternal-Uncle` | Father's brothers |
| `Maternal-Uncle` | Mother's brothers |
| `Parental-Aunt` | Father's sisters |
| `Maternal-Aunt` | Mother's sisters |
| `Sister-In-Law` | Spouse's sisters, Wives of siblings |
| `Brother-In-Law` | Spouse's brothers, Husbands of siblings |
| `Son` | |
| `Daughter` | |
| `Siblings` | |