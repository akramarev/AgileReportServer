var report = 
{
	//_id: '53e5f052b59f97de723a3527'
	user = 
	{
		_id: ObjectId("53e5f052b59f97de723a3527"),
		company: 
		{
			_id: ObjectId("53e8e7f7aeded062518b8848"),
			name: "Contoso"
		},
		email: 'kramarew@gmail.com',
		firstName: "Andrew",
		lastName: "Kramarev",
	}
	status: 'Draft',
	dateCreatedUtc: '08/02/2014',
	dateUpdatedUtc: '08/02/2014',
	dateCompletedUtc: '08/02/2014',
	body: 
	[
		{
			name: 'Done',
			value: 'What was done today?'
		},
		{
			name: 'In Progress',
			value: 'What\'s in progress still?'
		}
	]
}

// db.reports.insert(report)
// db.elements.find({attrs: {$elemMatch: name: 'Done'}})