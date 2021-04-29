curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id":"20a0bbd5-0bff-4c4e-9150-a8723d4bece8","name":"TOMY2"}' \
	http://localhost:8080/users

curl -d '{"id":"80a0bbd5-0bff-4c4e-9150-a8723d4bece8","name":"TOMY1"}' -H "Content-Type: application/json" -X POST  http://localhost:8080/users
curl -H "Content-Type: application/json"  http://localhost:8080/users

curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id":"4050122b-9659-487f-8ce2-2ff6bbc80ae9","title":"TomyDePana","duration_in_seconds":5,"category":"Screencast"}' \
  http://localhost:8080/videos

#Http4s
curl -d '["60a0bbd5-0bff-4c4e-9150-a8723d4bece8","TOMY6"]' -H "Content-Type: application/json" -X POST  http://localhost:8080/users2
