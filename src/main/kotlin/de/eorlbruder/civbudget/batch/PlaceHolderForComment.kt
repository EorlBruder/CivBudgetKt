package de.eorlbruder.civbudget.batch

// TODO eorlbruder 01.04.2018 - REMOVE this file when I have network again to put the information here in an appropriate
// Issue

// TODO eorlbruder 01.04.2018 - Now we will need to somehow work with batchjobs. We'll need to dig into that. Things
// to read about: BatchJob-Scheduling, using different domain-objects of the same table, Starting the BatchJob,
// how does Spring handle all of this, Putting everything into different Gradle-Modules. I still want the same (big
// Codebase, because it's easier to have everything in one place. Maybe if this blows up I'll change that and use
// multiple repositories for everything, but for now this is totally fine (and totally over-engineered, but I'm also
// using this project to try out some microservicey stuff...

// TODO eorlbruder 01.04.2018 - Oh, and now we should look into Checkstyle (or similiar) for the disallowed imports.
// Everything at the level de.eorlbruder.civbudget.*.* should be it's own, separate module and not reference anything
// else