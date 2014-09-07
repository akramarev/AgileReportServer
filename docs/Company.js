var company = 
{
	_id: ObjectId("53e8e7f7aeded062518b8848"),
	owner: 
	{
		_id: ObjectId("53e8e868aeded062518b8849"),
		email: "kramarew@gmail.com",
		firstName: "Andrew",
		lastName: "Kramarev"
	},
	name: "Contoso",
	dateCreatedUtc: "08/02/2014"
}
db.companies.insert(company)