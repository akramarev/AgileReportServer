var report = 
{
	_id: 'asdasd',
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

// db.elements.find({attrs: {$elemMatch: name: 'Done'}})