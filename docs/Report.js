var report = 
{
	//_id: '53e5f052b59f97de723a3527'
	user:
	{
		_id: ObjectId("53e8e868aeded062518b8849"),
		company: 
		{
			_id: ObjectId("53e8e7f7aeded062518b8848"),
			name: "Contoso"
		},
		email: 'kramarew@gmail.com',
		firstName: "Andrew",
		lastName: "Kramarev"
	},
	status: 'Draft',
	dateCreatedUtc: '08/02/2014',
	dateUpdatedUtc: '08/02/2014',
	dateCompletedUtc: '08/02/2014',
	body:
	[
		{
			name: 'Done',
			value: 'Almost everything'
		},
		{
			name: 'In Progress',
			value: 'not so much'
		}
	]
}

// db.reports.insert(report)
// db.elements.find({attrs: {$elemMatch: name: 'Done'}})