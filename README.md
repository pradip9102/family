# Meet The Family

## Problem context
Our story is set in the planet of Lengaburu......in the distant, distant galaxy of Tara B.And our protagonists are King
Shan, Queen Anga & their family.

King Shan is the emperor of Lengaburu and has been ruling the planet for the last 350 years (they have long lives in
Lengaburu, you see!). Let’s write some code to get to know the family.

This coding problem is for backend and fullstack developers and tests object oriented fundamentals.

## Features
+ Given a 'name' and a 'relationship', output the people corresponding to the relationship in the order in which they were added in the family tree.
+ Add a child to any family in the tree through the mother.

## I/O Format
| Format | Input | Output |
|:-------|:------|:-------|
| *Recreate planet | `LENGABURU [king_name] [queen_name]` | `PLANET_CREATED` |
| Add a child | `ADD_CHILD [mother_name] [child_name] [gender]` | `CHILD_ADDITION_SUCCEEDED` |
| *Marriage | `MARRIAGE [husband_name] [wife_name]` | `MARRIED` |
| Find related people | `GET_RELATIONSHIP [person_name] [relationship]` | `[[name_1]...]` |

## Sample I/O scenarios
| Sample | Input | Output |
|-------:|:------|:-------|
| 1. | `LENGABURU Shan Anga` | `PLANET_CREATED` |
| 2. | `MARRIAGE Chit Amba` | `MARRIED` | 
| 3. | `ADD_CHILD Pjali Srutak Male` | `PERSON_NOT_FOUND` |
| 4. | `GET_RELATIONSHIP Pjali Son` | `PERSON_NOT_FOUND` |
| 5. | `ADD_CHILD Asva Vani Female` | `CHILD_ADDITION_FAILED` |
| 6. | `GET_RELATIONSHIP Vasa Siblings` | `NONE` |
| 7. | `GET_RELATIONSHIP Atya Sister-In-Law` | `Krpi Satvy` |

## Supported relationships
Simple relationships: Father, Mother, Sibling, Child

Composite relationships: others

| Relationships | Description |
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
| *`Brother` | |
| *`Sister` | |

## Assumptions
+ A new token `LENGABURU` is being used to reset the system, which simply introduces king and queen to the system during
 initialization to keep things simple. [e.g. `LENGABURU Shan Anga`]
+ `LENGABURU [king_name] [queen_name]` must be the first command to the system.
+ Adding new child without mother name should be allow with a wilcard(?) mother name. [e.g. `ADD_CHILD ? Aria Female`] 

## How to run


