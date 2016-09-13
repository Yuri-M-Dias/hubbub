class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//Permalinks
		"/timeline/chuck_norris" {
			controller = "post"
			action = "timeline"
			id = "chuck_norris"
		}
		"/users/$id" {
			controller = "post"
			action = "timeline"
		}
		"/users/$userId/feed/$format?" {
			controller = "post"
			action = "feed"
			constraints {
				format(inList: ['rss', 'atom'])
			}
		}

		"/users/$userId/stats"{
            controller = "user"
			action = "stats"
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
