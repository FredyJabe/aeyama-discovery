function planetGrid(planet, sectorSize){
    planet['\x67\x72\x69\x64']=PlanetGrid['\x63\x72\x65\x61\x74\x65'](sectorSize);
    planet['\x73\x65\x63\x74\x6f\x72\x73']['\x65\x6e\x73\x75\x72\x65\x43\x61\x70\x61\x63\x69\x74\x79'](planet['\x67\x72\x69\x64']['\x74\x69\x6c\x65\x73']['\x6c\x65\x6e\x67\x74\x68']);
    for(let i=0;i<planet['\x67\x72\x69\x64']['\x74\x69\x6c\x65\x73']['\x6c\x65\x6e\x67\x74\x68'];i++){
        planet['\x73\x65\x63\x74\x6f\x72\x73']['\x61\x64\x64'](new Sector(planet,planet['\x67\x72\x69\x64']['\x74\x69\x6c\x65\x73'][i]));
    };
    planet['\x73\x65\x63\x74\x6f\x72\x41\x70\x70\x72\x6f\x78\x52\x61\x64\x69\x75\x73']=planet['\x73\x65\x63\x74\x6f\x72\x73']['\x66\x69\x72\x73\x74']()['\x74\x69\x6c\x65']['\x76']['\x64\x73\x74'](planet['\x73\x65\x63\x74\x6f\x72\x73']['\x66\x69\x72\x73\x74']()['\x74\x69\x6c\x65']['\x63\x6f\x72\x6e\x65\x72\x73'][0]['\x76']);
};
exports.planetGrid = planetGrid;
// This document is public and can be used by anyone.
// Direct copy, do not have to do any modification can be used.
// Just reference it from the planet code
//本文件公开，任何人可以使用。直接复制，不用做任何修改就可以使用。只需在星球代码那边引用即可