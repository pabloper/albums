# Albums

The two things well solved are MvvM reactive architecture together with a Dependency Injection.

Because of time constraints there are a series of things that I couldn't solve but I am aware of. If I had to pick only 2:
From the usability perspective there is no mechanism to recover from network errors, and the user would need to resume/kill the app
From the architecture perspective there is no domain layer and network services are directly used in view models.

But from a production ready app I am missing the following features or architectural changes:
- Add mechanism to recover from network errors (Swipe refresh layout?)
- Proper domain layer, don't use the network service directly in the view model
- Division between network/ui models (mappers). Add types for Ids -> AlbumId.
- Repository layer (offline storage?), possible easy win add network cache to Retrofit
- Integrate MvvM with live data to keep the ui state
- Unit tests for all non framework non model classes. Configure RX java test schedulers.
- Navigator (or navigator component from architecture components)
- Standardize view dimensions (margins, element sizes, colors)
- Add touch feedback to list elements
- Move some hardcoded strings to constants like fragment tags or the generic fragment id
- Network errors ui layer generic error display
- Beautify the app following material design guidelines
