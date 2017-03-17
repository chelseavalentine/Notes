# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 13: Emergency response

### Test-induced emergency

* Google has scheduled disaster & emergency testing where SREs break the system, and observe how they fail, in order to make changes to improve reliability and prevent future failures from recurring
  - can help identify weaknesses or hidden dependencies

### Change-induced emergency

* emergencies induced by changes to configurations which result in unexpected & undesired behavior

### Process-induced emergency

* emergencies induced by processes (e.g. automation)

### Learn from the past. Don't repeat it.

* keep a history of outages

* ask the big, sometimes improbable, "what if..." questions

* encourage proactive testing

## Ch 14: Managing incidents

### Elements of incident management process

* everyone involved in the incident needs to have their own exclusive role, thereby separating responsibilities
  - need more staff and to re-delegate if any one person is overloaded
  - roles include:
    + incident commander assigns responsibilities according to need & priority; holds all positions that they haven't delegated; if appropriate, has ability to remove roadblocks preventing Ops from working most effectively
    + ops lead works with the commander by applying ops tools; the ops team is the only group modifying the system during the incident
    + communicator issues periodic updates to the incident response teams & stakeholders (usually via emails); may also keep the incident document accurate & updated
    + planner supports Ops by dealing w/ long-term issues, e.g. filing bugs, ordering food, arranging handoffs, and tracking how the system has diverged so it can be reverted upon incident resolution

* interested parties need to know where to interact w/ the commander (e.g. "War Room")

* commander needs to keep a living incident document; should be editable by several people

* need to perform a clear, live handoff of the commander role at the end of the current commander's  workday, and change should be communicated to incident party

### When to declare an incident

* do you need to involve a second team in fixing the problem?

* is the outage visible to customers?

* is the issue unsolved even after an hour's concentrated analysis?

## Ch 15: Postmortem culture - Learning from failure

### Google's postmortem philosophy

* writing a postmortem is a learning opportunity for the entire company

* postmortem triggers:
  - user-visible downtime or degradation beyond a certain threshold
  - data loss of any kind
  - on-call engineer intervention (release rollback, rerouting of traffic, etc.)
  - a resolution time above some threshold
  - a monitoring failure (which usually implies manual incident discovery)
  - a stakeholder requesting a postmortem for an event

* postmortems should:
  - identify contributing causes 
  - not indict any individuals or teams for bad/inappropriate behavior
  - calls out where and how services can be improved
  - not identify who the end-user(s) is/are

### Collaborate and share knowledge

* tools for sharing postmortems should include the following features:
  - real-time collaboration
  - an open commenting/annotation system
  - email notifications

* review criteria in publishing a postmortem:
  - was the key incident data collected for posterity?
  - are the impact assessments complete?
  - was the root cause sufficiently deep?
  - is the action plan appropriate and are resulting bug fixes at appropriate priority?
  - did we share the outcome with relevant stakeholders?

## Ch 16: Tracking outages

* all alert notifications for SRE share a central replicated system tracking whether a human has acknowledged receipt of a notification
  - system escalates to next destination if it hasn't been acknowledged after a configured interval

* "_Outalator"_ is Google's outage tracker, which passively receives all alerts sent by monitoring systems, and allows them to annotate, group, and analyze the data
  - can aggregate multiple alerts into a single incident
  - can tag notifications, e.g. tag something as a false-positive alert, test event, mistargeted email; tag its cause, action, etc.
