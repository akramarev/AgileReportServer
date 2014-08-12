var user = 
{
	//_id: ObjectId("53e8e868aeded062518b8849"),
	company: 
	{
		_id: ObjectId("53e8e7f7aeded062518b8848"),
		name: "Contoso"
	},
	email: 'kramarew@gmail.com',
	firstName: "Andrew",
	lastName: "Kramarev",
	dateCreatedUtc: '08/02/2014',
	password:
	{
		passwordHash: '4c2209f9f3924d31102bd84a',
		passwordSalt: '209f9f392'
	},
	roles:
	[
		'Administrator'
	]
}

//db.users.insert(user)