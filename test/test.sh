TODOS_BASE_URL=http://localhost:8080/todomvc-services-javaee6/todos

echo ""
echo "==============="
echo "testing get all"
echo "==============="
curl -H "Content-Type: application/json" -X GET $TODOS_BASE_URL
echo ""
echo "==============="
echo "testing post record"
echo "==============="
curl -i -H "Content-Type: application/json" -X POST --data @example.json $TODOS_BASE_URL
echo ""
echo "==============="
echo "testing put record"
echo "==============="
curl -i -H "Content-Type: application/json" -X PUT --data @example.json $TODOS_BASE_URL
echo ""
echo "==============="
echo "testing get all"
echo "==============="
curl -H "Content-Type: application/json" -X GET $TODOS_BASE_URL
echo ""

# Using sed and awk since fewer assumed requirements for *nix. Based on:
# http://stackoverflow.com/a/1956034/178651
# http://stackoverflow.com/a/1521498/178651
# TODO: Fix. Currently is leaving one undeleted periodically.
echo "Deleting all records."
curl -H "Content-Type: application/json" -X GET $TODOS_BASE_URL | sed -e 's/[{}]/''/g' | awk -v RS=',"' -F: '/^\"id\"/ {print $2}' > cleanup.txt
while read p; do
  echo ""
  echo "==============="
  echo "testing get record"
  echo "==============="
  curl -H "Content-Type: application/json" -X GET $TODOS_BASE_URL/$p
  echo ""
  echo "==============="
  echo "testing delete record"
  echo "==============="
  echo "Deleting record $p:"
  curl -H "Content-Type: application/json" -X DELETE $TODOS_BASE_URL/$p
done < cleanup.txt
rm cleanup.txt
echo "Done cleaning up."
