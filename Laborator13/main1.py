from datetime import date
from functional import seq
from collections import namedtuple

Person = namedtuple('Person', ['first_name', 'last_name', 'date_of_birth', 'email'])

persons = [
    Person('John', 'Doe', date(1960, 11, 3), 'jdoe@example.com'),
    Person('Ellen', 'Smith', date(1992, 5, 13), 'ellensmith@example.com'),
    Person('Jane', 'White', date(1986, 2, 1), 'janewhite@example.com'),
    Person('Bill', 'Jackson', date(1999, 11, 6), 'bjackson@example.com'),
    Person('John', 'Smith', date(1975, 7, 14), 'johnsmith@example.com'),
    Person('Jack', 'Williams', date(2005, 5, 28), '')
]

# cel mai tanar si cel mai batran
youngest = seq(persons).sorted(lambda p: p.date_of_birth, reverse=True).first()
oldest = seq(persons).sorted(lambda p: p.date_of_birth).first()
print(f"Youngest: {youngest.first_name} {youngest.last_name}")
print(f"Oldest: {oldest.first_name} {oldest.last_name}\n")

# minori (sub 18 ani)
today = date.today()
underage = seq(persons).filter(lambda p: (today - p.date_of_birth).days // 365 < 18).to_list()
print(f"Underage: {underage}\n")

# lista de emailuri
emails = seq(persons).map(lambda p: p.email).to_list()
print(f"Emails: {emails}\n")

# map nume -> email
emails_map = seq(persons).map(lambda p: (f"{p.first_name} {p.last_name}", p.email)).to_dict()
print(f"Emails map: {emails_map}\n")

# map email -> persoana
email_person_map = seq(persons).map(lambda p: (p.email, p)).to_dict()
print(f"Email-person map:")
seq(email_person_map.items()).for_each(print)
print()

# group by luna nasterii
by_month = seq(persons).group_by(lambda p: p.date_of_birth.month)
print(f"Birthdays each month: {by_month}\n")

# partition - inainte/dupa 1980
before, after = seq(persons).partition(lambda p: p.date_of_birth.year <= 1980)
print(f"Born before 1980: {list(before)}")
print(f"Born after 1980: {list(after)}\n")

# prenume distincte
names = seq(persons).map(lambda p: p.first_name).distinct()
print(f"First names: {', '.join(names)}\n")

# varsta medie
avg_age = seq(persons).map(lambda p: (today - p.date_of_birth).days // 365).average()
print(f"Average age: {avg_age}\n")

# count Smith
smiths = seq(persons).filter(lambda p: p.last_name == 'Smith').len()
print(f"Number of Smiths: {smiths}\n")

# cauta John
john_list = seq(persons).filter(lambda p: p.first_name == 'John').to_list()
if john_list:
    print(john_list[0])
else:
    print("No one named John was found")
print()

# cauta Thomas
thomas_list = seq(persons).filter(lambda p: p.first_name == 'Thomas').to_list()
if thomas_list:
    print(thomas_list[0])
else:
    print("No one named Thomas was found")
print()

# cineva fara email
no_email = seq(persons).exists(lambda p: p.email == '')
print(f"Any with missing email: {no_email}")