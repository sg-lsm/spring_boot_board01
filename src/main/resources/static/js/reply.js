async function getList({bno, page, size, goLast}){
    const result = await axios.get(`/replies/list/${bno}`)
    return result.data;
}