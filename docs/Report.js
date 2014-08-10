var report = 
{
	/*_id: 'asdasd',*/
	companyId: ObjectId("53e5efcfb59f97de723a3526"),
	userId: ObjectId("53e5f052b59f97de723a3527"),
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