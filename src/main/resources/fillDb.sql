use bookkeeping;
insert into departments (name)
values ('Directors'),
       ('IT'),
       ('Bookkeeping'),
       ('PR');

insert into employees (first_name, last_name, pather_name, position, salary)
values ('Nikita', 'Tsapov', 'Vladimirovich', 'Chief Executive Officer', 5000.00),
       ('Elon', 'Musk', null, 'Chief Product Architect', 3000.00),
       ('Mark', 'Zuckerberg', null, 'Senior software developer', 2000.00),
       ('Pavel', 'Durov', 'Valerievich', 'full stack team lead', 1000.00),
       ('Galina', 'Stepanova', 'Semyonovna', 'bookkeeper', 500.00),
       ('Vasily', 'Pupkin', 'Stepanovich', 'PR specialist', 700.00);

insert into projects (cost, date_beg, date_end, date_end_real, name, department_id)
values (100000.00, '2022-01-01', '2022-12-31', null, 'Relocation of headquarters to Norway', (select id from departments where departments.name='Directors')),
       (10000.00, '2022-02-10', '2022-07-10', null, 'Create Web 3 application', (select id from departments where departments.name='IT')),
       (200.00, '2022-03-04', '2022-03-25', '2022-03-16', 'Calculate new salaries', (select id from departments where departments.name='Bookkeeping')),
       (100.00, '2022-03-18', '2022-03-25', '2022-03-17', 'Create and fill instagram account', (select id from departments where departments.name='PR')),
       (100.00, '2022-03-18', '2022-03-25', '2022-03-17', 'Create and fill youtube account', (select id from departments where departments.name='PR')),
       (100.00, '2022-03-18', '2022-03-25', '2022-03-17', 'Create and fill vk account', (select id from departments where departments.name='PR'));

insert into departments_employees (department_id, employee_id)
values ((select id from departments where name='Directors'), (select id from employees where position='Chief Executive Officer')),
       ((select id from departments where name='Directors'), (select id from employees where position='Chief Product Architect')),
       ((select id from departments where name='IT'), (select id from employees where position='Senior software developer')),
       ((select id from departments where name='IT'), (select id from employees where position='full stack team lead')),
       ((select id from departments where name='Bookkeeping'), (select id from employees where position='bookkeeper')),
       ((select id from departments where name='PR'), (select id from employees where position='PR specialist'));
