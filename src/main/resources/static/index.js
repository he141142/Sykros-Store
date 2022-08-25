const server = "http://localhost:8002"

window.sendData = function(url, config,redirect) {
  return  fetch(url, { ...config })
  .then(response => {
    if (!response.ok) {
      return response.json()
      .catch(() => {
        // Couldn't parse the JSON
        throw new Error(response.status);
      })
      .then(({message}) => {
        // Got valid JSON with error response, use it
        throw new Error(message || response.status);
      });
    }
    // Successful response, parse the JSON and return the data
    if (redirect){
      window.location.href = `${server}${redirect}`;
    }
    return response.json();
  });
}

