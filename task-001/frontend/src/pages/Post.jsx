import axios from "axios";
import { useState, useEffect } from "react";
import Loading from "../components/Loading";

function Post() {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/posts")
      .then((res) => setData(res.data?.data))
      .catch((err) => {
        console.error("Error fetching data:", err);
        setError("Service Not Available! Please try again later.");
      });
  }, []);

  if (error) return <div className="text-red-600">{error}</div>;

  return <div>{data ? <div>{JSON.stringify(data)}</div> : <Loading />}</div>;
}

export default Post;
