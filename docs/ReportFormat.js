reportFormat = {
	company: 
	{
		_id: ObjectId("53e8e7f7aeded062518b8848"),
		name: "Contoso"
	},
	dateCreatedUtc: '08/02/2014',
	dateUpdatedUtc: '08/02/2014',
	dateCompletedUtc: '08/02/2014',
	body:
	[
		{
			name: 'Done',
			placeholder: 'What was done today?'
		},
		{
			name: 'In Progress',
			placeholder: 'What\'s in progress still?'
		},
		{
			name: 'Next Day',
			placeholder: 'What will be done next?'
		},
		{
			name: 'Reviewed',
			placeholder: 'What was reviewed today?'
		},
		{
			name: 'Questions',
			placeholder: 'Any questions?'
		}
	]
}
db.report_formats.insert(reportFormat)